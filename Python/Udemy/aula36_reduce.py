# função reduce
from functools import reduce
def somar (x, y):
    return x + y
lista = [2,3,4,8,32]
soma = reduce (somar, lista )
print(soma)