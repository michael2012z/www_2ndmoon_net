import sys
import os

os.chdir("img/600")
for i in range(1, 76):
    os.rename("T"+str(i)+".jpg", "S"+str(i)+".jpg")

