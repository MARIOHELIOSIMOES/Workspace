import math


def exercicio1():
    print("Exercicio 1")
    idade = int(input("Informe a Idade: "))
    if idade < 18:
        print("Menor de idade")
    elif idade >= 18 and idade < 60:
        print("Maior de idade")
    else:
        print("Idoso")
def exercicio2():
    print("\n\nExercicio2")

    nota1 = float(input("Informe a primeira nota: "))
    nota2 = float(input("Informe a segunda nota: "))
    media = (nota1 + nota2) / 2

    if media>=6 :
        print("Aprovado")
    else:
        print("Reprovado")

def exercicio3():
    print("Exercicio 3")
    print("Equação do segundo grau\n ax^2 + bx + c")
    a = int(input("Informe o valor de A: "))
    b = int(input("Informe o valor de B: "))
    c = int(input("Informe o valor de C: "))

    bascara = b * b - (4*a*c)
    if bascara < 0 :
        print("Não existe resultado")
        return

    x1 = (-b + math.sqrt(bascara))/2*a
    x2 = (b + math.sqrt(bascara)) / 2 * a

    print("Raizes: x1=",x1," x2 = ", x2)
    return

def exercicio4():
    print("Exercicio 4")
    lista = []
    for i in range(3):
        # lista.append(int(input("Informe um valor(" + str(i+1) +"): ")))
        lista.append(int(input(f"Informe um valor({i+1}): "))) #f antes da string para formatar
    print(lista)
    print(sorted(lista))

def exercicio5():
    print("Exercicio5")
    op = str(input("Informe o símbolo da operação: \n  + (soma)\n -(subtracao)\n *(multiplicação)\n \\(divisao)\n sua opção: "))
    op = op.strip()
    if(op=="+" or op=="-" or op=="*" or op =="/"):
        a = int(input("Informe o primeiro valor: "))
        b = int(input("Informe o segundo valor: "))
    else:
        print("Operação Inválida!")
        return

    if op == "+":
        print(a+b)
    elif op =="-":
        print(a-b)
    elif op=="*":
        print(a*b)
    elif op=="/":
        if(b==0):
            while(b==0):
                b = int(input("Informe um valor diferente de 0: "))
        print(a/b)
    else:
        print("Operação Inválida!")

exercicio5()