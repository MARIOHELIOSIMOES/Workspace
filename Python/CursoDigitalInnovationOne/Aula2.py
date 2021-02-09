a = int(input("Informe o valor de A: "))
b = 5
soma = a+b
subtracao = a - b
multi = a * b
divi = a/b
resto = a%b
print(type (soma))
print("soma: {a} + {b} = " .format(a=a,b=b) + str(soma))
print("Subtração: {}" .format(subtracao))
print("Multiplicação:" + str(multi))
print("Divisão: "+str(divi))
print("Resto: "+str(resto))
