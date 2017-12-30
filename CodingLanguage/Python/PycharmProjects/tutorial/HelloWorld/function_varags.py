#Somtimes you might want to define a function that can take any number of parameters, i.e. variable number of arguments
def total(a=5, *numbers, **phonebook):
    print 'a', a

    #iterate through all the itmes in tuple
    for single_item in numbers:
        print 'single_item', single_item

    #iterate through all the items in dictionary
    for first_part, second_part in phonebook.items():
        print first_part,second_part

print total(10, 1, 2, 3, 5, Jack = 1123, John = 2231, Inge = 1560 , Cwray = 223)
