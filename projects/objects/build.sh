#! /bin/bash

yasm -f elf64 $1 -o yasm.out
ld -s -o $2 yasm.out
chmod +x $2
rm -rf yasm.out

./$2