nome = "Mario"
sobrenome = "Simões"

nomecompleto = nome + " " + sobrenome

print(nomecompleto)
print(nomecompleto.lower())
print(nomecompleto.upper())
print(nomecompleto.strip()) # remove caracteres especiais

nomecompleto = "Mário Helio Simões"
print(nomecompleto)
listaNome = nomecompleto.split(" ") # divide a string em uma lista
print (listaNome)

indice = nomecompleto.find("eli") # Retorna o índice da posição buscada
print(indice)
print(nomecompleto[indice:]) # Imprime a string com inicio no indice até o final

print(nomecompleto.replace("elio","oile")) #substitui o valores da string