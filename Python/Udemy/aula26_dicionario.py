dicionario = {"A": "Letra A", "B": "Letra B", "C":"Letra C"}
print(dicionario)
print(dicionario["A"])
print(dicionario.pop("B")) #pop remove o item


for chave in dicionario:
    print(chave)
    print(dicionario[chave])