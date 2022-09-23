#### 문자열
# 길이 : len(str)
# 인덱싱 : str[n]
# 위치 찾기 : str.index('a') ->a문자의 위치, 없으면 에러발생
# 위치 찾기 : str.find('a') ->a문자의 위치, 없으면 -1
# 자르기 : str[0:3]
# 문자반복 : str*3 #stringstring
# 문자가 알파벳인지 : x.isalpha()
# 리스트를 문자열로 : ''.join(result)
# 리스트를 문자열로(숫자포함된 경우) : ''.join(str(s) for s in str_list) 
# 리스트처럼 for 사용가능

#### 숫자
# 길이는 str로 바꿔서 : len(str(a))
# 절대값 : abs(num)
# 반올림 : round(num,3)
# 나누기 :  a/b
# 나머지 :  a%b
# 몫 : a//b
# 거듭제곱 :  a**b


#### input
## 기본
#문자 : input()
#숫자 : int(input())

# import sys
# input = sys.stdin.readline().rstrip() 
# input()

## 여러개
# 숫자 : map(int,input().split())
# 리스트_숫자 : list(map(int, input().split()))

## 리스트안에 입력
# list = []
# for _ in range(n):
#     v = int(input())
#     list.append(v)



### 리스트 List
## 초기화
# list = []
# list = [0] * n   = [0, 0, 0]
# list = [0 for _ in range(n)]   = [0, 0, 0]
# list = [[0] for _ in range(n)]   = [[0], [0], [0]]
# list = [[0] * 2 for _ in range(n)]   = [[0,0], [0,0], [0,0]]
# for i in range(4):
#     print(i)    => 0,1,2,3
# [i for i in range(20) if i % 2 == 1 ] #홀수로 20까지 숫자 [1,3,5,7,9,11,13,15,17,19]
# [i * i for i in range(1,10)] #1~9까지 제곱수 [1,4,9,16,25,36,49,64,81] 

## 정렬
## 본체 정렬(값 변화)
# 뒤집기(desc아님) : list.reverse()
# 정렬(오름차순) : list.sort()
# 정렬(내림차순) : list.sort(reverse=True)
# 문자정렬(원소길에 따라) :list.sort(key=len)

## 결과 반환(본체 변화x) , 딕셔너리 가능
#뒤집기 : v = list(reversed(x))
#정렬(오름차순) : v = sorted(x)
#정렬(내림차순) : v = sorted(x, reverse=True)
#딕션너리 정렬(첫번째요소로 정렬) : v = sorted(x, key=lambda, r:r[0], reverse=False)

##활용
#길이 : len(list)
#개수 : list.count()
#찾기(True,False로 나옴) : 'a' in list , 'a' not in list
#찾기 : list.index(value) : 찾는 첫번째 위치가 나옴 
#추가 : list.append(val)
#위치에 추가  : a.insert(2,5) #2에5추가 [1,2,5,2,3] 위치를 찾아 더하기떄문에 느림 
#인덱싱 : list[n]
#바꾸기 : list[n] = val
#지우기 : del list[n]
#지우기(값) : list.remove('a')
    #remove 대신 아래와 같이 사용 권장 
    # remove_set={3,5}
    # result = [i for i in a if i not in remove_set] #[2,2]
#자르기[a,b,c] : list[:1](처음부터 1번전까지 a), list[1:3](1번부터 3번전까지 b,c), list[2:](2번부터 끝까지 c)

######
# [] -> 리스트(list) : 수정가능, 여러개의 값을 한번에 다루기 위한 컨데이너 
# () -> 듀플(Tuple) : 수정불가, 여러개의 값을 한번에 다루기 위한 컨테이너
# {} -> 셋(set) :  집합형태를 쉽게처리하기위한 데이터 타입, key가 없다, index사용불가, 중복허용하지 않음, 순서가 없다
# {:} -> 딕셔너리(Dicionary) : 여러개의 값을 한번에 다루기위한 컨테이너, Key-Value, key는 유니크, Value는 수정가능 