import math

from TFigure import *


class TCylinder(TFigure):

    def V(self):
        return math.pi * self.h * (self.r ** 2)

    def S(self):
        return 2 * math.pi * self.r * (self.h + self.r)

    def __init__(self, h, r):
        self.h = h
        self.r = r

    def __str__(self):
        return "Cylinder: " + f"radius ={self.r} " + f"height = {self.h} " + f"Volume = {round(self.V(), 3)} "
