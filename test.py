print("hello python")

#range(10) : 9까지 연속 정수 생성
#len(문자) : 문자길이
#ord(문자) : 유니코드 정수 반환
#chr(정수) : 유니모드 문자 반환
#[[0] * 3 for _ in range(3)] : 매트릭스 생성 [[0, 0, 0], [0, 0, 0], [0, 0, 0]]


#문자열 입력
# str = input()
# str = input("입력해주세요 : ") 

#숫자입력
# num = int(input())

#문자 여러개
# str1, str2 = input().split()  => input().split(',') : ,로 구분 
# strList = list(input().split())


#숫자 여러개
# num1, num2 = map(int, input().split())
# numList = list(map(int, input().split()))
# num1, num2 = list(map(int, input().split())) #이것도 가능?????

#####  딕셔너리
# {key:value}
# dic = {'검은색':'black', '빨간색': 'red'}

# 추가
# dic['녹색'] = 'green'

#삭제
# del dic['검은색']

#존재 확인
# chk = '녹색' in dic => True

#value 
# dic.get('녹색') => green

#list
# list(dic.keys())
# list(dic.vlaue())

#두개다 가져오기
# dic.items()  => dict_items([('검은색', 'black'), ('빨간색', 'red'), ...])

#모두 지우기
# dic.clear() => None


#리스트
# list = [1,2,3,4]

#슬라이싱
# list[:2] => [1,2]
# list[2:] => [3,4]
# list[0:2] => [1,2]

# 변경
# list[0] = 5
# list[0:2] = 5,6

# 삭제
# del list[0]
# del list[0:2]

# 마지막 추가
# list.append(5)

# 삽입 추가
# list.insert(1,5)

# 갯수
# list.count(2) #2가 몇개

# 정렬
# list.sort()
# list.sort(reverse=True)
# list.reverse() #뒤집기 

# 위치
# list.index(2) #값2의 위치 반환 

# 제거
# list.remove(2) #값2를 삭제
# list.pop(2) #2번째 값 제거(꺼내기) 


import heapq

q = []

heapq.heappush(q,3)
heapq.heappush(q,2)
heapq.heappush(q,7)
heapq.heappush(q,0)

while q:
    print(heapq.heappop(q))