from TFigure import *


class TPyramid(TFigure):

    def S(self):
        return (self.p * self.a) / 2

    def V(self):
        return (self.p * self.a) / 2 / 2 * (1/3) * self.h

    def __init__(self, a, p):
        self.a = a
        self.p = p

    def __str__(self):
        return "Pyramid: " + f"apothem = {self.a} " + f"perimeter = {self.p} " + f"Square = {self.S()} "
