def dobro(x):
    if(x%2==0):
        return x
def exercicio1():
    lista1 = [1,2,3,4,5,6,7,8,9,10]
    lista2 = []
    lista2 = [i for i in lista1 if i%2==0]
    print(lista2)

exercicio1()