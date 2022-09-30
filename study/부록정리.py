#자료형
import imp
from this import s
from typing import Counter


def study_01():
    #### 수 자료형
    a = .3 + 0.6
    print(a)
    print('반올림 : ', round(a,4))
    
    a = 7
    b = 3
    print('나누기 : ', a/b)
    print('나머지 : ', a%b)
    print('몫 : ', a//b)
    print('거듭제곱 : ', a**b)
    
    ##### 리스트
    a = [1,2,3,4,5,6,7]
    print(a[5]) #6
    a = list() #빈리스트 선언1 []
    a = [] #빈리스트 선언2 []
    print(a[-2]) #뒤에서 2번째 6
    print(a[1:4]) #1~4번째 [2,3,4]
    
    a=[1,2,3]
    a.append(2) #삽입 [1,2,3,2]
    a.sort() #오름차순 [1,2,2,3]  
    a.sort(reverse = True) #내림차순 [3,2,2,1]
    a.reverse #뒤집기 [1,2,2,3]
    a.insert(2,5) #2에5추가 [1,2,5,2,3] #위치를 찾아 더하기떄문에 느림 
    a.count(2) #2가몇개인가 2
    a.remove(1) #값이 1인 데이터 삭제 [2,5,2,3] #특정값을 찾아 제거하기때문에 느림
    #remove 대신 아래와 같이 사용 권장 
    remove_set={3,5}
    result = [i for i in a if i not in remove_set] #[2,2]
    
    n=10
    a = [0] *10 #초기화 [0,0,0,0,0,0,0,0,0,0]
    
    array = [i for i in range(20) if i % 2 == 1 ] #홀수로 20까지 숫자 [1,3,5,7,9,11,13,15,17,19]
    #위와 같이
    array = []
    for i in range(20):
        if i%2 ==1:
            array.append(i)
    
    array = [i * i for i in range(1,10)] #1~9까지 제곱수 [1,4,9,16,25,36,49,64,81]        
    
    n=3 
    m=4
    array = [[0] * m for _ in range(n)] # nxm 크기의 2차원 초기화(반드시 사용) [[0,0,0,0], [0,0,0,0], [0,0,0,0]] , _(언더바) 단순반복
    
    ###### 문자열 
    a = "string"
    print(a*3) #stringstring
    print(a[2:4]) #ri 2이상4초과 사이 
    
    ###### 듀플
    #듀플은 한 번 선언된 값을 변경할 수 없다.
    #듀플은 소괄호() 사용, 리스트는 대괄호[] 사용    
    a = (1,2,3,4)

    ##### 사전
    #키와 값으로 이루어짐 
    data = dict()
    data['사과'] = 'apple'
    data['바나나'] = 'banana'
    print(data) #{'사과':'apple','바나나','banana'}
    key_list = data.key() #dict_keys(['사과','바나나'])
    value_list = data.values() #dict_values(['apple','banana'])    
    
    #집합
    #중복을 허용하지 않는다, 순서가 없다
    
    #집합 자료형 초기화 방법1
    data = {1,1,3,4,4,5} #{1,2,3,4,5,}
    data = set([1,2,3,4,4,5]) #{1,2,3,4,5,}
    data.add(4)
    data.update([5,6]) #여러개 추가
    data.remove(3) #값이3삭제
    
    a = set([1,2,3,4,5,])
    b = set([3,4,5,6,7])
    print('합집합 :', a|b) #{1,2,3,4,5,6,7}
    print('교집합 :', a&b) #{3,4,5}
    print('차집합 :', a-b) #{1,2}

def study_02():
    ###조건문
    # if a조건문:
    # elif a조건문에 해당않는 b조건문:
    # else a,b 조건문에 해당않는 :
    # X in 리스트/문자열: 리스트에 X가 있으면 True
    # X not in 리스트/문자열: 문자열에 X가 없으면 True 
    
    ### 함수
    # def 함수명(매개변수):
    #  실행할 소스코드
    #  return 반환값 
    # global을 이용하면 함수 밖의 변수 사용 
    
    #람다식
    a = (lambda a, b: a+b)(3,7)
    print(a)
    
    #입출력
    # input() 한줄의 문자열, 느림
    list(map(int, input().split())) # 공백으로 문자를 나눈뒤 map이용하여 모든 원소를 int함수 적용후 list[]로 변환 
    n, m, k = map(int, input().split()) # 적은 수 데이터 입력시 3 5 7입력이면 각 n=3 m=5 k=7 
    
    # sys 라이브러리가 있는경우 입력 사용, 데이터수가 1,000만개이상이거나 탐색범위크기가 1,000억개가 넘으면 input()은 느림
    #readline() : 엔터가 줄 바꿈 기호로 입력됨
    #restrip() :  위의 공백문자 제거
    import sys
    str = sys.stdin.readline().rstrip() 
    
    
    ########## 표준 라이브러리
    #내장 함수 : print() input() sorted() sum() min() max() 등
    eval("(3+5) * 7") #문자열 수학식을 계산
    sorted([9,1,8]) #오름차순 [1,8,9]
    sorted([9,1,8], reverse=True) #내림차순 [9,8,1]
    sorted([('홍길동',35), ('이순신',75), ('아무개',50)], key = lambda x: x[1], reverse=True) #숫자 내림차순 [('이순신',75), ('아무개',50), ('홍길동',35)]
    data = [9,1,8,5,4]
    data.sort() #리스트의 경우 sort() 사용 
        
    #itertools : 반복 데이터 처리 
    from itertools import permutations, combinations, product, combinations_with_replacement
    data = ['A','B','C']
    list(permutations(data,3)) #리스트에서 3개를 뽑아 모든 순열 구하기(순서고려, 각순서대로 여러 경우의수)
    list(combinations(data,2)) #리스트에서 2개를 뽑는 모든 조합 구하기 (순서x, 갯수만큼만 나오게끔)
    list(product(data, repeat=2)) # 2개를 뽑는 모든 순열 구하기 (중복허용)
    list(combinations_with_replacement(data,2)) #2개를 뽑는 모든 조합 구하기(중복허용)
    
    #heapq : 힙기능 제공, 우선순위 큐 기능을 구현하기 위해 사용, 최단경로
    #최소값으로 정렬
    import heapq
    def heapsort(iterable):
        h = []
        result = []
        for value in iterable:
            heapq.heappush(h,value) #순서대로 힙에 삽입
        for i in range(len(h)):
            result.append(heapq.heappop(h)) #힙에 삽입된 원소 순서대로 꺼내어 담기
        return result
    result = heapsort([1,3,5,7,9,2,4,6,8,0]) #[0,1,2,3,4,5,6,7,8,9]
    
    #최대힙(max heap)을 제공하지 않아 구현해야된다.
    #내림차순 힙 정렬
    def heapsortR(iterable):
        h = []
        result = []
        for value in iterable:
            heapq.heappush(h, -value) #순서대로 삽입, -를 이용하여 내림차순 정렬         
        for i in range(len(h)):
            result.append(-heapq.heappop(h))
        return result
    result = heapsortR([1,3,5,7,9,2,4,6,8,0]) #[9,8,7,6,5,4,3,2,1,0]
    
    #기존 리스트를 힙으로
    # heapq.heapify(list)
    
    
    #bisect: 이진탐색(Binary Search)기능 제공
    #bisect_left(a,x):정렬된 순서를 유지하면서 리스트 a에 데이터 x를 삽입할 가장 왼쪽 인덱스를 찾는 메서드
    #bisect_rigth(a,x):저영된 순서를 유지하도록 리스트 a에 데이터x를 삽입할 가장 오른쪽 인데스를 찾는 메서드
    #[1,2,4,4,8]이 있을때 bisect_left(a,4)는 2를 반환 bisect_rigth(a,4)는 4를 반환 
    from bisect import bisect_left, bisect_right
    def count_by_range(a,left_value, right_value):
        right_index = bisect_right(a, right_value)
        left_index = bisect_left(a, left_value)
        return right_index - left_index
    a = [1,2,3,3,3,3,4,4,8,9]
    print(count_by_range(a,4,4)) #값이 4인데이터 갯수 =2
    print(count_by_range(a,-1,3)) #값이 [-1,3] 범위에 있는 데이터 갯수 = 6
    
    #colections: 덱, 카운터 등 유용한 자료구조를 포함
    #보통 deque를 사용해 큐 구현, 삽입시 append(), 삭제시 popleft() -> 먼저 들어온 원소는 먼저 나가게 됨
    from collections import deque
    data = deque([2,3,4])
    data.appendleft(1)
    data.append(5)
    print(data) #deque([1,2,3,4,5])
    print(list(data)) #[1,2,3,4,5]
    #counter
    counter =Counter(['red', 'blue','red','green','blue','blue'])
    print(counter['blue']) #3
    print(dict(counter)) #사전자료형 {'red':2,'blue':3,'green':1}
    
    #math : 수학적 기능
    import math
    print(math.factorial(5)) #5! 120
    print(math.sqrt(7)) #7의 제곱근 2.645751...
    print(math.gcd(21,14)) #최대공약수 7
    print(math.pi) #파이
    print(math.e) #자연상수 e
    
    
    ######
    # [] -> 리스트(list) : 수정가능, 여러개의 값을 한번에 다루기 위한 컨데이너 
    # () -> 듀플(Tuple) : 수정불가, 여러개의 값을 한번에 다루기 위한 컨테이너
    # {} -> 셋(set) :  집합형태를 쉽게처리하기위한 데이터 타입, key가 없다, index사용불가, 중복허용하지 않음, 순서가 없다
    # {:} -> 딕셔너리(Dicionary) : 여러개의 값을 한번에 다루기위한 컨테이너, Key-Value, key는 유니크, Value는 수정가능 
    
### 실행
study_02()    