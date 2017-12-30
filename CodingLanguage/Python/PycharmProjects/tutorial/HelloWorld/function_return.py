# the return statement is used to return from a function i.e. break out of the function. We can optionally return a value from the function as well
def maximum(x,y):
    if x > y:
        return x
    elif x==y :
        return 'The numbers are equal'
    else:
        return y

print(maximum(2,3))
