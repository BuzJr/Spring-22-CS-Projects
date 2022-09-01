#Nathan Pawlas(pxx2rz) and Ali Bohliga(egr5ey)
"""
After reviewing our initial concept we decided it was too complex and decided to shoot for a more reasonable goal
of creating a space-invader like game that will have with the optional features we are using being: Restart from Game Over,
Timer(for keeping score), Inter-session Progress (High Score), and Enemies
"""
import gamebox
import random
import pygame

camera = gamebox.Camera(800,600)
#Initialization
character = gamebox.from_image(400,550,'Space Invader Ship.png')
#Cates, Preston. “Space Invaders Ship Transparent, HD PNG Download - Kindpng.” KindPNG.com, https://www.kindpng.com/imgv/hwoJbRi_space-invaders-ship-transparent-hd-png-download/.
alive = True
enemies = []
game_over = gamebox.from_text(400, 300, "Game Over", 72, "red")
counter = 0
score = 0
start = 0
progressive_difficulty = 0
projectiles = [ ]
walls = [gamebox.from_color(50,500,'blue',25,25),
gamebox.from_color(100,500,'blue',25,25),
gamebox.from_color(150,500,'blue',25,25),
gamebox.from_color(250,500,'blue',25,25),
gamebox.from_color(300,500,'blue',25,25),
gamebox.from_color(350,500,'blue',25,25),
gamebox.from_color(450,500,'blue',25,25),
gamebox.from_color(500,500,'blue',25,25),
gamebox.from_color(550,500,'blue',25,25),
gamebox.from_color(650,500,'blue',25,25),
gamebox.from_color(700,500,'blue',25,25),
gamebox.from_color(750,500,'blue',25,25),
gamebox.from_color(75,475,'blue',25,25),
gamebox.from_color(125,475,'blue',25,25),
gamebox.from_color(275,475,'blue',25,25),
gamebox.from_color(325,475,'blue',25,25),
gamebox.from_color(475,475,'blue',25,25),
gamebox.from_color(525,475,'blue',25,25),
gamebox.from_color(675,475,'blue',25,25),
gamebox.from_color(725,475,'blue',25,25),
gamebox.from_color(100,450,'blue',25,25),
gamebox.from_color(300,450,'blue',25,25),
gamebox.from_color(500,450,'blue',25,25),
gamebox.from_color(700,450,'blue',25,25),
         ]

def Write_Highscore(score):
    """
    Opens and reads a file called Highscore.txt. Stores the current score inside the file as a string,
    if it exceeds the score already stored in the file.
    :param score: The final score of the game when the Game Over Screen appears
    """
    try:
        f = open('Highscore.txt','r')
    except FileNotFoundError:
        f = open('Highscore.txt','w')
    file1 = open("Highscore.txt", 'r+')
    content = file1.readline()
    highscore = []
    for row in content:
        highscore.append(row)
    if not highscore:
        score = str(score)
        file1.write(score)
        return
    file1.seek(0)
    oldscore = ''
    for x in highscore:
        oldscore += x
    oldscore = int(oldscore)
    if score > oldscore:
        score = str(score)
        file1.write(score)
    else:
        oldscore = str(oldscore)
        file1.write(oldscore)

    file1.truncate()
    file1.close()

def tick(keys):
    """
    This function serves to animate the screen by updating the game 30 ticks a second. This function takes in
    the players input, causing the display to change based on the inputs that happen, as well as the "physics" and
    "interactables" that are updated every tick.
    :param keys: The input keys pressed by the player (LEFT, RIGHT) which control the playable character
    :return: Updates the display creating a new visual, 30 times a second
    """
    global counter,alive,walls,game_over,score, enemies, projectiles, start, progressive_difficulty
    camera.clear("tan")
    if start == 1:
        counter += 1
    # -START SCREEN-
    if start == 0:
        camera.clear('black')
        title = gamebox.from_text(400, 150, "Spacey Cubes", 100, 'blue')
        credits = gamebox.from_text(400, 250, "By: Nathan Pawlas (pxx2rz) and Ali Bohliga (egr5ey)", 35, 'gray')
        instruction1 = gamebox.from_text(400, 300, "Protect the blue squares from the red!", 50, 'red')
        instruction2 = gamebox.from_text(400, 350, "Use the left and right arrow keys to move", 40, 'gray')
        instruction3 = gamebox.from_text(400, 400, "and the spacebar to shoot.", 40, 'gray')
        instruction4 = gamebox.from_text(400,500,"Press SPACEBAR to Start",75,"green")
        try:
            file = open("Highscore.txt",'r')
            highscore = file.readline()
            highscore_display = gamebox.from_text(400, 450, "Highscore: " + highscore, 40, 'red')
        except FileNotFoundError:
            highscore_display = gamebox.from_text(400,450, "Highscore: 0", 40,'red')
        camera.draw(title)
        camera.draw(credits)
        camera.draw(instruction1)
        camera.draw(instruction2)
        camera.draw(instruction3)
        camera.draw(instruction4)
        camera.draw(highscore_display)
        if pygame.K_SPACE in keys:
            start = 1

    #-Character Action-
    if pygame.K_LEFT in keys and alive == True and start == 1:
        character.x -= 10
    if pygame.K_RIGHT in keys and alive == True and start == 1:
        character.x += 10
    if pygame.K_SPACE in keys and alive == True and start == 1:
       if counter%5 == 0:
           x = character.x
           y = character.y
           new_projectile = gamebox.from_color(x,y,'black', 5, 10)
           projectiles.append(new_projectile)

    #-Boundaries-
    if character.x < -5:
        character.x = -5
    if character.x > 805:
        character.x = 805

    #-Enemies_Creation-
    if alive == True and start == 1:
        if counter%120 == 0 and len(enemies) <= 100 and score != 0:  #This creates a new batch of enemies every 4 seconds (capped at 100)
            x = 3 + progressive_difficulty
            if counter % 600 == 0 and progressive_difficulty <= 97:
                progressive_difficulty += 1  #increases the number of enemies that spawns every 20 seconds
            if alive == False:
                progressive_difficulty = 0
            while x>0:
                random_x_pos = random.randint(100,700)
                new_enemy = gamebox.from_color(random_x_pos, 100, 'red', 35, 35)
                enemies.append(new_enemy)
                x -= 1
        if score == 0 and counter%30 == 0:
            x = 3
            while x > 0:
                random_x_pos = random.randint(100, 700)
                new_enemy = gamebox.from_color(random_x_pos, 100, 'red', 35, 35)
                enemies.append(new_enemy)
                x -= 1

    #Projectile Movement
    if alive == True:
        for each in projectiles:
            each.y -=5
        for each in projectiles:
            if each.y<0:
                projectiles.remove(each)
        for each in enemies:
            for x in projectiles:
                if each.touches(x):
                    enemies.remove(each)
                    projectiles.remove(x)

    #-Enemies Random Movement-
    if alive == True:
        if counter%25 == 0:
            for each in enemies:
                y = random.randint(10,75)
                x = random. randint(-35,35)
                each.x -= x
                each.y += y

    #-Enemy Boundaries and Attack-
    temp_enemies = enemies.copy()
    temp_walls = walls.copy()
    for x in enemies:
        for i in enemies:
            if x.touches(i):
                x.move_both_to_stop_overlapping(i, 5)
        if x.y > 600:
            enemies.remove(x)
        if x.x > 800:
            x.x -= 10
        if x.x < 0:
            x.x += 10
    for i in walls:
        for x in enemies:
            if i.touches(x):
                walls.remove(i)

    #-Drawing/Display-
    if start == 1:
        if len(walls)==0:
            alive = False
        for each in enemies:
            camera.draw(each)
        for each in walls:
            camera.draw(each)
        for each in projectiles:
            camera.draw(each)
        camera.draw(character)
    if alive == False:
        camera.clear('tan')
        game_over.y = camera.y  # Make sure message appears on screen
        camera.y += 0
        camera.draw(game_over)
        finish_display = gamebox.from_text(400, camera.y + 50, "Final Score: " + str(score), 30, 'red')
        camera.draw(finish_display)
        Write_Highscore(score)
        file = open("Highscore.txt",'r')
        highscore = file.readline()
        highscore_display = gamebox.from_text(400, camera.y + 100, "Highscore: "+highscore, 30, 'red')
        camera.draw(highscore_display)
        restart = gamebox.from_text(400, camera.y+150, "Press SPACEBAR to Try Again", 50, 'blue')
        camera.draw(restart)
    if counter % 30 == 0 and alive == True and start == 1:
        score += 1
    score_display = gamebox.from_text(40, camera.y + 250, str(score), 50, "red")
    if start == 1:
        camera.draw(score_display)
    camera.display()

    # RESTARTS THE GAME
    if alive == False and pygame.K_SPACE in keys:
        counter = 0
        score = 0
        progressive_difficulty = 0
        start = 1
        alive = True
        enemies = []
        walls = [gamebox.from_color(50, 500, 'blue', 25, 25),
                  gamebox.from_color(100,500,'blue',25,25),
                  gamebox.from_color(150,500,'blue',25,25),
                  gamebox.from_color(250,500,'blue',25,25),
                  gamebox.from_color(300,500,'blue',25,25),
                  gamebox.from_color(350,500,'blue',25,25),
                  gamebox.from_color(450,500,'blue',25,25),
                  gamebox.from_color(500,500,'blue',25,25),
                  gamebox.from_color(550,500,'blue',25,25),
                  gamebox.from_color(650,500,'blue',25,25),
                  gamebox.from_color(700,500,'blue',25,25),
                  gamebox.from_color(750,500,'blue',25,25),
                  gamebox.from_color(75,475,'blue',25,25),
                  gamebox.from_color(125,475,'blue',25,25),
                  gamebox.from_color(275,475,'blue',25,25),
                  gamebox.from_color(325,475,'blue',25,25),
                  gamebox.from_color(475,475,'blue',25,25),
                  gamebox.from_color(525,475,'blue',25,25),
                  gamebox.from_color(675,475,'blue',25,25),
                  gamebox.from_color(725,475,'blue',25,25),
                  gamebox.from_color(100,450,'blue',25,25),
                  gamebox.from_color(300,450,'blue',25,25),
                  gamebox.from_color(500,450,'blue',25,25),
                  gamebox.from_color(700,450,'blue',25,25),
                 ]

gamebox.timer_loop(30, tick)