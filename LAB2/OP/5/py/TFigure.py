from abc import ABC, abstractmethod


class TFigure(ABC):

    @abstractmethod
    def S(self):
        pass

    @abstractmethod
    def V(self):
        pass
