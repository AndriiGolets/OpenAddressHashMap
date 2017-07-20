# OpenAddress HashMap

#### This project provides HashMap with OpenAddress collission solving

I create interface SimpleMap<Integer, Long>  with three main methods - 'get', 'put', 'size'
and create three implementatoins:

1) HashMapOpenAddressFixSize - Use linnear probing for distribute collision elements. Has a fixed size

2) HashMapOpenAddressResizable -  Use linnear probing for distribute collision elements. Use clastering principle for 
resizing. When we have group of elements with size bigger then cluster size we do resize(). Cluster size stands as
logarithm with base 2 from table length. Also its limited with load factor (default 0.5).

This two maps used simple ( hashCode % (tableLength-1) + 1 ) hash method.
Null key is suported. Null key always placed to 0 cell of the table. Table size is increazed and shifted on one cell
for supporting null values.

3) SimpleMapToHashMapAdapter - SimpleMap implementation uses standart java.util.HashMap internaly.

I create jUnit tests for SimpleMap interface where we can choose implementation.

For understanding perfomance of my Maps I create performance tests:
In this tests I used three types of input data:
1) random keys
2) sequentialkeys (1, 2, 3, ... n)
3) power of two keys (2, 4, 8, 16 ... 2^n)

Here the test results for 10k, 100k, 1m, 10m keys 

#### Random Tests : 

+ FixSize Put 10000 : 8 
+ FixSize Get 10000 : 4 
+ FixSize Put 100000 : 32 
+ FixSize Get 100000 : 27 
+ FixSize Put 1000000 : 286 
+ FixSize Get 1000000 : 163 
+ FixSize Put 10000000 : 4314 
+ FixSize Get 10000000 : 4137 
+ Resizable Put 10000 : 5 
+ Resizable Get 10000 : 2 
+ Resizable Put 100000 : 24 
+ Resizable Get 100000 : 14 
+ Resizable Put 1000000 : 159 
+ Resizable Get 1000000 : 127 
+ Resizable Put 10000000 : 3841 
+ Resizable Get 10000000 : 1974 
+ Standard Put 10000 : 5 
+ Standard Get 10000 : 2 
+ Standard Put 100000 : 27 
+ Standard Get 100000 : 14 
+ Standard Put 1000000 : 133 
+ Standard Get 1000000 : 104 
+ Standard Put 10000000 : 1684 
+ Standard Get 10000000 : 2913 

#### Sequential Tests : 

+ FixSize Put 10000 : 1
+ FixSize Get 10000 : 1
+ FixSize Put 100000 : 5
+ FixSize Get 100000 : 3
+ FixSize Put 1000000 : 24
+ FixSize Get 1000000 : 10
+ FixSize Put 10000000 : 3921
+ FixSize Get 10000000 : 143
+ Resizable Put 10000 : 1
+ Resizable Get 10000 : 0
+ Resizable Put 100000 : 7
+ Resizable Get 100000 : 5
+ Resizable Put 1000000 : 82
+ Resizable Get 1000000 : 90
+ Resizable Put 10000000 : 5320
+ Resizable Get 10000000 : 169
+ Standard Put 10000 : 1
+ Standard Get 10000 : 0
+ Standard Put 100000 : 4
+ Standard Get 100000 : 2
+ Standard Put 1000000 : 30
+ Standard Get 1000000 : 7
+ Standard Put 10000000 : 646
+ Standard Get 10000000 : 2890

#### PowerOf2 Tests : 

+ FixSize  : 42
+ Resizable  : 16
+ Standard  : 52

For FixeSize -  HashMapOpenAddressFixSize I always used max 10m initialLength

We can see good performance comparably to java.util.HashMap in 'Power of two' tests
and for Get operations
