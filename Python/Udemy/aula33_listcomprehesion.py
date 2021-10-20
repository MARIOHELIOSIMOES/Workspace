def exemplo1():
    print("List comprehesion")
    x = [1,2,3,4,5]
    # y = [valor_a_adicionar laço ]
    y = [i**2 for i in x]

    print(x)
    print(y)

def exemplo2():
    print("List comprehesion 2")
    x = [1, 2, 3, 4, 5]
    # y = [valor_a_adicionar laço condição]
    y = [i for i in x if i%2==1]

    print(x)
    print(y)

exemplo1()
exemplo2()

