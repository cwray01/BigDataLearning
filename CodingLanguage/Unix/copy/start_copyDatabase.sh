#!/bin/bash

echo 'copy db start....'

dbBack=/databaseBack
nfsDir=/targetDbNfs
state=0

cd /

if [ ! -d "$nfsDir" ];then
  mkdir -p $nfsDir
fi

## mount target db
echo 'ready mount target db...'
mount -t nfs $2 $nfsDir

if [ $? -eq 0 ]
  then
    echo 'mount target db success...'
  else
    echo 'mount target db fail...'
    exit 1
fi

echo 'ready copy....'
## cp will tip y/n, so use \cp 
\cp -rf $dbBack/$1/* $nfsDir/

if [ $? -eq 0 ]
  then
    echo 'copy success...'
  else
    echo 'copy fail...'
    $state=1
fi

umount $nfsDir

exit $state