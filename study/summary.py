#### 문자열
# 길이 : len(str)
# 인덱싱 : str[n]
# 자르기 : str[0:3]
# 문자가 알파벳인지 : x.isalpha()
# 리스트를 문자열로 : ''.join(result)
# 리스트를 문자열로(숫자포함된 경우) : ''.join(str(s) for s in str_list) 
# 리스트처럼 for 사용가능

#### 숫자
# 길이는 str로 바꿔서 : len(str(a))


#### input
## 기본
#문자 : input()
#숫자 : int(input())

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
# for i in range(4):
#     print(i)    => 0,1,2,3

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
#추가 : list.append(val)
#인덱싱 : list[n]
#바꾸기 : list[n] = val
#지우기 : del list[n]
#지우기(값) : list.remove('a')
#자르기[a,b,c] : list[:1](처음부터 1번전까지 a), list[1:3](1번부터 3번전까지 b,c), list[2:](2번부터 끝까지 c)