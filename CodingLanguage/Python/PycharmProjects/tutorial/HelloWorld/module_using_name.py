#When a module is imported for the first time, the code it contains get executed
#We can use this to make the module behave in different ways depending on whether it is being used by itself or being imoprted from another module

if __name__ == '__main__':
    print 'This program is being run by itself'
else:
    print 'I am being imported from another module'
