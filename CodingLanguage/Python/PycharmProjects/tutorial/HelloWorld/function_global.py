x = 50

def func():
    global x

    print 'x is', x
    x = 2
    print 'Change global x to', x

func()
print 'Value of x is', x