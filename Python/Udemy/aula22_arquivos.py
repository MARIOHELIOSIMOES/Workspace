# -*- coding: utf-8 -*-

#abertura de arquivo
arquivo = open("arquivo.txt")
linhas = arquivo.readlines()
for linha in linhas:
    print(linha)


# criação de arquivo
arquivo = open("arquivo2.txt","a") # r - leitura, w - sobreescreve, a - leitura e escrita ; r+ w+ e a+
arquivo.write ("Escrevendo no arquivo 2\n")
arquivo.close()
