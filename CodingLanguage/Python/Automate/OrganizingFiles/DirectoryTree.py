import os
for folderName,subfolders,filenames in os.walk('/Users/cwray/Documents/GitHub/BigDataLearning/CodingLanguage/Python/Automate/OrganizingFiles/delicious'):
    print('The current folder is ' + folderName)

    for subfolder in subfolders:
        print('Subfolder of ' + folderName + ':' + subfolder)
    for filename in filenames:
        print('File Inside' + folderName + ':'+filename)
    print('')