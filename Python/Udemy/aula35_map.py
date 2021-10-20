#map

def dobro(x):
    return x*2

valor = [1,2,3,4,5]
print(valor)

valorDobro = map(dobro, valor)
print(valorDobro)
valorDobro = list(valorDobro) # nessário converter o map para list
print(valorDobro)