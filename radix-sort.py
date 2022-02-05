import urllib
import requests
from functools import reduce
import array

def book_to_words(book_url='https://www.gutenberg.org/files/84/84-0.txt'):
    booktxt = urllib.request.urlopen(book_url).read().decode()
    bookascii = booktxt.encode('ascii','replace')
    return bookascii.split()

def __lengStr(array):
    maxl = 0
    for i in array:
        if maxl < len(i):        #goes through the list of strings looking for the string with the highest amount of characters 
            maxl = len(i)        #replaces the max char size to the size of the longest string
    return maxl
    
def __buckToList(array):
    return reduce(lambda x, y: x+y, array)      #this adds all the buckets together and makes a complete list

def main():
    booklist = book_to_words()
    sortedarray = radix_a_book(booklist, __lengStr(booklist))
    print(sortedarray)
        
def radix_a_book(array, strNum):
    lis = array
    string = ''
    for k in range(0,len(lis)):
        add = lis[k]
        adstr = add.decode('ascii', 'replace')
        string = string + ' ' + adstr
    string = string.split()
    padArray =[r+' ' * (__lengStr(lis)-len(r)) for r in string]
    for i in reversed(range(0, strNum)):            #reads from right to left so that we can get the most significant bit last 
        bucket = [[] for s in range (128)]          #make 128 buckets for every ascii character
        for j in range(0, len(padArray)):              #for every item in the array of words
            thing = padArray[j]                        #get the word
            byte = thing.encode('ascii', 'replace') #put it into byte type and find the ascii number for the radix placement into the bucket
            numb = byte[i]                          #makes the number to be placed into the correct bucket
            convert = padArray[j].strip()              #strips all padding of the string
            bucket[numb].append(convert.encode('ascii', 'replace'))           #converts string into byte type to be added to bucket 
        A = __buckToList(bucket)                    #turns the bucket into a list 
    return A        

main()                                              #main function