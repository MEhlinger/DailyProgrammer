# Conway's Game of Life, by Marshall Ehlinger

import random, time, pygame, sys
from pygame.locals import *


# Establish CONSTANTS
CELLSTATES = [' ', 'X']

WHITE = (255, 255, 255)
BLUE = (0, 0, 255)
BLACK = (0, 0, 0)


# dimension determines sides of screen/square, in number of cells. Cells are always 20x20 pixels.
DIMENSION =  35



# Initialize pygame
pygame.init()
mainClock = pygame.time.Clock()
screenxy = [(DIMENSION * 20), (DIMENSION * 20)]
mywin = pygame.display.set_mode(screenxy, 0, 32)
pygame.display.set_caption('Conway\'s Game of Life')

# Define Functions


#contains logic for updating grid according the the rules of the Game of Life

def updateGrid(grid):
    potentialGrid = []
    
    # active column set to -1, so each iteration can add one and identify the column of the cell. Refers to index.
    actColumn = -1
    
    for column in grid:
        potentialGrid.append([])
        actColumn += 1
        
        # same deal with actRow as actColumn above. Resets to -1 for each column, though. Refers to index.
        actRow = -1
        
        for cell in column:
            actRow += 1
            
            # establish neighbors list, to later be checked against rules. 8 neighbors (includes diagonals)
            neighbors = []

            # set variables for each possible relationship, both +1, -1, as well as +9 and -9 for wraparounds
            plusColumn = actColumn + 1
            minusColumn = actColumn - 1
            plusRow = actRow + 1
            minusRow = actRow - 1

            wrapColumnLeft= actColumn + (DIMENSION-1)
            wrapColumnRight= actColumn - (DIMENSION -1)
            wrapRowBottom = actRow + (DIMENSION -1)
            wrapRowTop = actRow - (DIMENSION-1)

            if actRow + 1 > (DIMENSION-1):
                plusRow = wrapRowTop
            elif actRow - 1 < 0:
                minusRow = wrapRowBottom

            if actColumn + 1 > (DIMENSION-1):
                plusColumn = wrapColumnRight
            elif actColumn - 1 < 0:
                minusColumn = wrapColumnLeft

            
            # add each neighbor to the list
            neighborCell = grid[minusColumn][(plusRow)]
            neighbors.append(neighborCell)
            
            neighborCell = grid[minusColumn][(actRow)]
            neighbors.append(neighborCell)

            neighborCell = grid[minusColumn][(minusRow)]
            neighbors.append(neighborCell)

            neighborCell = grid[actColumn][(minusRow)]
            neighbors.append(neighborCell)

            neighborCell = grid[plusColumn][(minusRow)]
            neighbors.append(neighborCell)

            neighborCell = grid[plusColumn][(actRow)]
            neighbors.append(neighborCell)

            neighborCell = grid[plusColumn][(plusRow)]
            neighbors.append(neighborCell)

            neighborCell = grid[actColumn][(plusRow)]
            neighbors.append(neighborCell)


            # check neighbors for relevant rules
            # THE RULES OF CONWAY'S GAME OF LIFE:
            # (1) Any live cell with < 2 live neighbors dies
            # (2) Any live cell with 2 or 3 live neighbors is unchanged
            # (3) Any live cell with > 3 live neighbors dies
            # (4) Any dead cell with exactly three live neighbours becomes alive


            cellChange = False
        

            if cell == 'X':
                if neighbors.count('X') > 3 or neighbors.count('X') < 2:
                    newCell = ' '
                    cellChange = True
            elif cell == ' ':
                if neighbors.count('X') == 3:
                    newCell = 'X'
                    cellChange = True


            if cellChange == True:
                potentialGrid[actColumn].append(newCell)
                
            else:
                potentialGrid[actColumn].append(cell)
        


        
    return potentialGrid

# eventCheck runs check for events (quitting)

def eventCheck():
    for event in pygame.event.get():
        if event.type == QUIT:
            pygame.quit()
            sys.exit()

# drawScreen updates the screen. 'grid' is the source grid to draw from

def drawScreen(grid):
    mywin.fill(BLACK)
    updateGuiGrid(grid)
    pygame.display.update()
    mainClock.tick(7)

# updateGuiGrid updates the visual grid to match the lifeGrid. Also: colors.

def updateGuiGrid(grid):
    for x in range (0, DIMENSION):
        for y in range (0, DIMENSION):

            # Random, mottled colors need to be defined below:
            DARKGREENS = (0, random.randint(80,140), 0)
            LIGHTGREENS = (0, random.randint(185,255), 0)
            DARKBLUES = (0, 0, random.randint(80,140))
            LIGHTBLUES = (0, 0, random.randint(185,255))
            DARKPURPLES = (random.randint(80,140), 0, random.randint(80,140))
            LIGHTPURPLES = (random.randint(185,255), 0, random.randint(185,255))
            CHAOSCOLOR = (random.randint(80,255), random.randint(80,255), random.randint(80,255))
            greyScaler = random.randint(20,60)
            GREYSCALES = (greyScaler, greyScaler, greyScaler)
            
            
            cellLocation = ((20 * x), (20 * y), (20), (20))
            cellLocationCircle = (((20 * x)+10), ((20 * y)+10))

            

            if colorSet == 1:
                if grid[x][y] == 'X':
                    pygame.draw.rect(mywin, CHAOSCOLOR, cellLocation, 0)
                elif grid[x][y] == ' ':
                    pygame.draw.rect(mywin, BLACK, cellLocation, 0)
            elif colorSet == 2:
                if grid[x][y] == 'X':
                    pygame.draw.rect(mywin, DARKGREENS, cellLocation, 0)
                elif grid[x][y] == ' ':
                    pygame.draw.rect(mywin, LIGHTGREENS, cellLocation, 0)
            # colorset 3 is chaos on black with CIRCULAR cells, DEAD AND ALIVE
            elif colorSet == 3:
                if grid[x][y] == 'X':
                    drawCircleCell(mywin, CHAOSCOLOR, cellLocationCircle)
                elif grid[x][y] == ' ':
                    drawCircleCell(mywin, BLACK, cellLocationCircle)
            # colorset 4 is greens with CIRCULAR cells
            elif colorSet == 4:
                if grid[x][y] == 'X':
                    drawCircleCell(mywin, DARKGREENS, cellLocationCircle)
                elif grid[x][y] == ' ':
                    drawCircleCell(mywin, LIGHTGREENS, cellLocationCircle)


# draws a circular cell. Takes surface, color, and location

def drawCircleCell(surface, color, location):
        return pygame.draw.circle(surface, color, location, 10, 0) 




# ___MAIN GAME___


# define non-constant variables

generation = 1
colorSet = 3



# Build the empty array/grid

lifeGrid = []
for x in range (0, DIMENSION):
    column = []
    for y in range (0, DIMENSION):
        column.append(' ')
    lifeGrid.append(column)
 


# establish starting live cells at random. randomizer chooses between 2 cellstate constants, slightly different to add variety
for x in range(0, (DIMENSION-1)):
    cellX = x
    for y in range(0, (DIMENSION -1)):
        cellY = y
        lifeGrid[cellX][cellY] = random.choice(CELLSTATES)



# _MAIN GAME LOOP_

while True:
    eventCheck()
    drawScreen(lifeGrid)
    lifeGrid = updateGrid(lifeGrid)
    for event in pygame.event.get():
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_q:
               colorSet = 1 
            if event.key == pygame.K_w:
                colorSet = 2
            if event.key == pygame.K_e:
                colorSet = 3
            if event.key == pygame.K_r:
                colorSet = 4
    generation += 1
    print(generation)
    
    
    

        
