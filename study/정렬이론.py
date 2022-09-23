###정렬
#기준에 따라 나열 

from re import A
import re


def study_01():        
    ### 선택정렬 : 느림
    # 1. 0번째 값 선택하고 다음값과 비교하여 작으면 바꿈
    # 2. 반복
    # N-1반복 
    array = [7,5,9,0,3,1,6,2,4,8]
    for i in range(len(array)):
        min_index = i #가장작은 원소의 인덱스
        for j in range(i+1, len(array)):
            if array[min_index] > array[j]:
                min_index = j
        array[i], array[min_index] = array[min_index], array[i] #스와프
    print(array) #[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        
   
    
    ### 삽입정렬 : 거의 정렬이 되어 있는 상태면 빠름
    # 1. 2번째 값부터 아래값들에서 작은 자리고 이동
    # 2. 반복 
    array = [7,5,9,0,3,1,6,2,4,8]        
    for i in range(1,len(array)):
        for j in range(i,0,-1): #인덱스 i부터 1까지 감소하며 반복하는 문법
            if array[j] < array[j-1]: #한 칸씩 왼쪽으로 이동
                array[j], array[j-1] = array[j - 1], array[j]
            else: #자기보다 작은 값을 만나면 그 위치에 멈춤
                break
    print(array) #[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    
    
    
    ### 퀵정렬 : 리스트를 피벗에 따라 분할하여 정렬
    # 피벗 : 교환하기 위한 기준 
    # 호어분할방식: 
    # 1. 리스트에서 첫번째 데이터를 피벗으로 정한다.
    # 2. 리스트의 왼쪽에서 피벗보다 큰데이터를 오른쪽에서 피벗보다 작은데이터를 찾아 서로 교환
    # 3. 가운데까지 반복하고 가운데에 피벗데이터가 들어감
    # => 호어분할다음 피벗 양쪽을 똑같이 호어분할 진행하여 정렬 
    
    #방법01
    array = [5,7,9,0,3,1,6,2,4,8]
    def quick_sort(array, start, end):
        if start >= end: #원소가 한개인 경우 종료
            return
        pivot = start #피벗은 첫번째 원소 
        left = start + 1
        right = end
        while left <= right:
            #피벗보다 큰 데이터를 찾을 때까지 반복
            while left <= end and array[left] <= array[pivot]:
                left += 1
            #피벗보다 작은 데이터를 찾을 때까지 반복
            while right > start and array[right] >= array[pivot]:
                right -= 1
            if left > right: #엇갈렸다면 작은 데이터와 피벗을 교체
                array[right], array[pivot] = array[pivot], array[right]
            else: #엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
                array[left], array[right] = array[right], array[left]
        #분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행
        quick_sort(array, start, right-1)
        quick_sort(array, right+1, end)
    quick_sort(array, 0, len(array)-1)
    print(array) #[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    
    #방법02 : 피벗을 기준으로 작은값, 큰값리스트를 바로 나눔
    array = [5,7,9,0,3,1,6,2,4,8]
    def quick_sort(array):
        #리스트가 하나 이하의 원소만을 담고 있다면 종료
        if len(array) <= 1:
            return array
        pivot = array[0] 
        tail = array[1:] #피벗을 제외한 리스트
        left_side = [x for x in tail if x <= pivot] #분할된 왼쪽 부분, 피벗보다 작은 값들
        right_side = [x for x in tail if x > pivot] #분할된 오른쪽 부분, 피벗보다 큰 값들
        #분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬을 수행하고, 전체 리스트를 반환
        return quick_sort(left_side) + [pivot] + quick_sort(right_side)
    print(quick_sort(array)) #[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    
    
    
    ### 계수 정렬(Count Sort)
    #특정한 조건이 부합할 때만 사용할 수 있지만 매우 빠른 정렬 알고리즘
    #인덱스를 활용한다
    #모든 원소의 값이 0보다 크거나 같아고 가정
    array =[7,5,9,0,3,1,6,2,9,1,4,8,0,5,2]
    #모든 범위를 포함하는 리스트 선언(모든 값은 0으로 초기화)
    count = [0] * (max(array)+1)
    for i in range(len(array)):
        count[array[i]] += 1 #각 데이터에 해당하는 인덱스의 값 증가
    
    for i in range(len(count)):#리스트에 기록된 정보 확인
        for j in range(count[i]):
            print(i, end=' ') #0 0 1 1 2 2 3 4 5 5 6 7 8 9 9
            
    ### 정렬 라이브러리
    #sort()
    array = [7,5,9,0,3,1,6,2,4,8]
    
    result = sorted(array) #딕셔너리 자료형, 집합 자료형도 정렬가능
    print(result)
    
    array.sort() #리스트 변수가 하나 있을때
    print(array)
    
    array = [('바나나',2), ('사과',5), ('당근',3)]
    def setting(data):
        return data[1]
    result = sorted(array, key=setting)
    print(result) #[('바나나', 2), ('당근', 3), ('사과', 5)]
     
     
###실전문제
def study_02():    
    ###위에서 아래로
    #내림차순정렬 프로그램
    #첫줄 입력은 갯수N(1<=N<=500)
    #두번째 줄은 갯수입력 (1<=m<=100000) ->1~100,000 이하이므로 어떠한 정렬을 사용가능
    n = int(input())
    
    array = []
    for i in range(n):
        array.append(int(input()))
        
    #파이썬 기본 정렬 라이브러리
    array = sorted(array, reverse=True)
    for i in array:
        print(i, end=' ')    
            
def study_03():    
    ###성적이 낮은 순서로 학생 출력하기
    #학생수 1<=n<=100,000 -> 100,000 까지 가능하므로 최악의 시간이 걸릴수 있음 파이썬 라이브러리 사용하는게 좋음
    #이름, 점수로 받음, 점수는 100이하
    n = int(input())
    array = []
    for i in range(n):
        input_data = input().split()
        #이름은 문자열 그대로, 점수는 정수형
        array.append((input_data[0], int(input_data[1])))
    #키를 이용하여 점수를 기준으로 정렬
    array = sorted(array, key=lambda student: student[1])
    
    #정렬이 수행된 결과를 출력
    for student in array:
        print(student[0], end=' ')          

def study_04():
    #두 배열의 원소 교체
    #두 배열중 한배열의 합이 최대가 되도록
    # N:배열길이 1 <= N <= 100,000 , K:최대교환횟수 0 <= K <= N  ->O(NlogN)을 보장하는 정렬알고리즘 사용
    # 배열은 10,000,000보다 작은 자연수 
    
    n,k = map(int, input().split())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    
    a.sort() #오름차순 정렬
    b.sort(reverse=True) #내림차순 정렬
    
    for i in range(k):
        if a[i] < b[i]:
            a[i], b[i] = b[i], a[i]
        else:
            break
    print(sum(a))

            
####### 실행용
study_03()
