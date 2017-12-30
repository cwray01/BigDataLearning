#if you have some functions with many parameters and you want to specify only some of them, then you can give values for such parameters by naming them

def func(a, b=5, c=10):
    print 'a is ', a, 'and b is ', b, 'and c is', c

func(3, 7)
func(25, c=24)
func(c=50, a=100)