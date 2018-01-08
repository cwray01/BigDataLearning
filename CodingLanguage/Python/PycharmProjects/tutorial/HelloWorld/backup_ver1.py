import os
import time

# 1. The files and directories to be backed up are
# specified in a list.
# Example on Mac OS X or Linux:
source = ['/Users/cwray/Documents/GitHub/BigDataLearning/CodingLanguage/Python/PycharmProjects/tutorial']
# Notice we had to use double quotes inside the string
# for names with spaces in it.

# 2. THe backup must be stored in a main backup directory
# Example on Mac OS X and Linux
target_dir = '/Users/cwray/Documents/GitHub/BigDataLearning/CodingLanguage/Python/PycharmProjects/tutorial/backup'
# Remember to change this to which folder you will be using

# 3. The files are backed up into a zip file
# 4. THe name of the zip archive is the current date and time
target = target_dir + os.sep + \
            time.strftime('%Y%m%d%H%M%S') + '.zip'

# Create tartget directory if it is not present
if not os.path.exists(target_dir):
    os.mkdir(target_dir) # make directory

# 5. we use the zip command to put the files in a zip archive
zip_command = 'zip -r {0} {1}'.format(target,' '.join(source))

# Run the backup
print 'Zip command is:'
print zip_command
print 'Running'

if os.system(zip_command) == 0:
    print 'Successful backup to', target_dir
else:
    print 'Backup Failed'