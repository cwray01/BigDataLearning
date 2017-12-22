number = 23
guess = int(input('Enter an tnteger : '))

if guess == number:
     #new block starts here
    print('Congratulations, you guessed it.')
    print('(but you do not win any prizes!)')
     #new block ends here
elif guess < number:
    # Another block
    print('No, it is a little higher than that')
    # You can do whatever you want in a block ...
else:
    print('No, it is a little lower than that')
    # you must have guesse > number to reach here
print('Done')
# this last statement is always executed,
# after the if statment is executed
