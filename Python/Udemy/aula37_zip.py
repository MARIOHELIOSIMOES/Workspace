#função zip

lista1 = [1,2,3,4,5]
lista2 = ["primeiro", "segundo", "Terceiro", "Quarto", "Quinto"]
lista3 = ["Um", "Dois", "Tres", "Quatro", "Cinco"]

for numero, numeral, n in zip(lista1, lista2, lista3): # função para concatenar listas
    print(numero, numeral, n)

for n in zip(lista1, lista2, lista3):
    print(n)