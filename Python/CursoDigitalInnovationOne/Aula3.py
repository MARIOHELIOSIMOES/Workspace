#a = int(input('Primeiro valor'))
# b = int(input('Segundo valor'))
# c = int(input('Terceiro valor'))
#
# if a > b and a > c:
#     print('O maior valor é {}'.format(a) )
# elif b > a and b > c:
#     print('O maior valor é {}' .format(b))
# else:
#     print('O maior valor é {}'.format(c))
#
# a = int(input('Insira um valor'))
# resto = a % 2
# if resto==0:
#     print("O número {} é par".format(a))
# else:
#     print('O número {} é impar'.format(a))

a = int(input('primeiro bimestre: '))

if(a>10 or a<0):
    a= int(input('Foi informado uma nota errada. Primeiro bimestre: '))

b = int(input('segundo bimestre: '))

if(b>10 or b<0):
    b= int(input('Foi informado uma nota errada. Segundo bimestre: '))
c = int(input('terceiro bimestre: '))

if(c>10 or c<0):
    c=int(input('Foi informado uma nota errada. Terceiro bimestre: '))
d = int(input('quarto bimestre: '))

if(d>10 or d<0):
    d=int(input('Foi informado uma nota errada. Quarto bimestre: '))

media = (a+b+c+d)/4

if(a>10 or a<0):
    print('Foi informado uma nota errada. Primeiro bimestre: ')

print("Média: {}".format(media))