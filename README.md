![alt text](https://github.com/VitaliiKolesnyk/AlgoritmPerformedApp/blob/master/Algoritms.jpg)

# SORT algoritms:
**Types:**
- BUBBLE_SORT
- MERGE_SORT
- SELECT_SORT
- INSERT_SORT
- QUICK_SORT

Method - POST

Perform sort algoritm - http://localhost:8082/algoritms/sort/{algoritmType} 

Perform all algoritms - http://localhost:8082/algoritms/allsort

Body:
JSON

{

    "inFile" : "",
    
    "outFile" : ""
}


# SEARCH algoritms:
**Types:**
- LINEAR_SEARCH
- BINARY_SEARCH

Method - POST

Perform sort algoritm - http://localhost:8082/algoritms/search/{algoritmType}

Perform all algoritms - http://localhost:8082/algoritms/allsearch


Body:
JSON

{

    "inFile" : "",
    
    "outFile" : "",
    
    "searchElement": 1
}

**inFile** - path to file with not sorted integer values.

**outFile** - path to file, where sorted integer values will be stored.

**Expeted files format** - txt. Integer values in inFile are expected to be split by \s.,;
