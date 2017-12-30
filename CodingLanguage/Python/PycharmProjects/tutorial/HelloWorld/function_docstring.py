# documentation strings are referred to by its shorter name docstrings. DocStrings are an important tool that you should make use of since if=t helps to document the program better and makes it eastier to understand.
#We can get the docstring back from when the program is actually running

def print_max(x, y):
    '''Print the maximum of two numbers.

    The two values must be integers.'''

    #convert to integers, if possible
    x = int(x)
    y = int(y)

    if x>y:
        print(x, 'is maximum')
    else:
        print(y, 'is maximum')

print_max(3,5)
print (print_max.__doc__)

# you can comment the last line of the program and see the difference.