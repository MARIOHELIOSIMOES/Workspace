lista_frutas = ["primeiro", "segundo", "Terceiro", "quarto", "Quinto"]
print(lista_frutas)
print(lista_frutas[2])
lista_numerico = [0,1,2,3,44,5]
print(lista_numerico)
print(lista_numerico[4])
lista_frutas.append("Melância")
lista_frutas.append("Limão")
print(lista_frutas)

if 44 in lista_numerico:
    print("Está na lista")

del lista_frutas[1]
print(lista_frutas)
del lista_numerico[:] # remove all
print(lista_numerico)