from PIL import Image
import math
import os

def distance(a, b):
    return math.sqrt(sum((x - y) ** 2 for x, y in zip(a, b)))

def closest_list(target, colourList):
    return min(colourList, key=lambda lst: distance(target, lst))

colourList = [
[127, 178, 56], #Grass
[247, 233, 163], #Sand
[255, 0, 0], #Lava
[160, 160, 255], #Ice
[167, 167, 167], #Iron
[0, 124, 0], #Leaves
[255, 255, 255], #Snow/Wool
[164, 168, 184], #Clay
[183, 106, 47], #Dirt
[112, 112, 112], #Stone
[64, 64, 255], #Water
[104, 83, 50], #Logs
[255, 252, 245], #Quartz
[216, 127, 51], #Orange wool
[178, 76, 216], #Magenta wool
[102, 153, 216], #Light blue wool
[229, 229, 51], #Yellow wool
[127, 204, 25], #Lime wool
[242, 127, 165], #Pink wool
[76, 76, 76], #Grey wool
[153, 153, 153], #Light grey wool
[76, 127, 153], #Cyan wool
[127, 63, 178], #Purple wool
[51, 76, 178], #Blue wool
[102, 76, 51], #Brown wool
[102, 127, 51], #Green wool
[153, 51, 51], #Red wool
[25, 25, 25], #Black wool
[250, 238, 77], #Gold block
[92, 219, 213], #Diamond block
[74, 128, 255], #Lapis block
[0, 217, 58], #Emerald
[21, 20, 31], #Obsidian
[112, 2, 0], #Netherrack
]


path = "../src/main/resources/assets/lumis_lots/textures/blocks/"
for (root, dirs, files) in os.walk(path):
    for file in files:
        image = Image.open(path + file)
        pixels = image.load()
        average = [0,0,0]
        for i in range(16):
            for j in range(16):
                for k in range(3):
                    average[k] = average[k] + pixels[i,j][k]
        for i in range(3):
            average[i] = round(average[i] / 256)
        average = closest_list(average, colourList)
        print(f"{colourList.index(average) * 4 + 6} - {str(average)} - {file}")

'''
102 - [102, 76, 51] - composting_dirt.png
50 - [104, 83, 50] - composted_dirt.png
6 - [127, 178, 56] - wet_sponge.png
38 - [183, 106, 47] - false_block.png
6 - [127, 178, 56] - true_block.png
'''