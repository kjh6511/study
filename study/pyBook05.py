####이진탐색

def study_01():
    #순차 탐색 : 리스트 안에 있는 특정한 데이터를 찾기 위해 앞에서부터 데이터를 하나씩 차례대로 확인하는 방법 O(N)
    def sequential_search(n, target, array):
        for i in range(n):
            if array[i] == target:
                return i+1
    print("생성할 원소 개수를 입력한 다음 한 칸 띄고 찾을 문자열을 입력하세요.")
    input_data  = input().split()
    n = int(input_data[0])
    target = input_data[1]
    print("앞서 적은 원소 개수만큼 문자열을 입력하세요. 구분은 띄어쓰기 한 칸으로 합니다.")
    array = input().split()  
    print(sequential_search(n,target,array))

def study_02():
    #이진탐색 (binary Search) : 배열 내부의 데이터가 정렬되어 있어야만 사용할 수 있다.  O(logN)
    # 찾을려는 데이터와 중간점위치에 있는 데이터를 반복적으로 비교
    
    #방법01 재귀함수 이용
    def binary_search_01(array, target, start, end):
        if start > end:
            return None
        mid = (start + end) //2
        if array[mid] == target:
            return mid
        elif array[mid] > target: #중간값보다 작은 경우 오른쪽 확인
            return binary_search_01(array, target, start, mid-1)
        else: #중간값보다 큰 경우 왼쪽 확인
            return binary_search_01(array,target, mid+1, end)
        
    n, target = list(map(int, input().split())) # 원소갯수와 찾고자하는 원소 
    array = list(map(int, input().split()))
    
    result = binary_search_01(array, target, 0, n-1)
    if result == None:
        print("원소가 존재하지 않습니다.")
    else:
        print(result + 1)
        
    #방법02 반복문 이용
    def binary_search_02(array,target, start, end):
        while start <= end:
            mid = (start + end) // 2
            if array[mid] == target:
                return mid
            elif array[mid] > target:
                end = mid -1
            else:
                start = mid +1
        return None
    n, target = list(map(int, input().split()))        
    array = list(map(int, input().split()))
    
    result02 = binary_search_02(array, target, 0, n-1)
    if result02 == None:
        print("원소가 존재하지 않습니다.")
    else: 
        print(result02 + 1) 
        
def study_03():
    #트리 자료구조
    #트리는 부모노드와 자식조드의 관계로 푠현된다
    #트리의 최상단 노드를 루트 노드라고 한다       
    #트리의 최하단 노드를 단말 노드라고 한다
    #트리에서 일부를 뗴어내도 트리 구조이며 이를 서브 트리라 한다
    #트리는 파일 시스템과 같이 계층적이고 정련된 데이터를 다루기에 적합하다.
    
    # 이진 탐색 트리
    #부모 노드보다 왼쪽 자식 노드가 작다
    #부모 노드보다 오른쪽 자식 노드가 크다 
    # 왼쪽 자식노드 < 부모노드 < 오른쪽 자식노드
    # 이진탐색처럼 찾는값이 부모보다 작으면 왼쪽만 탐색, 크면 오른쪽만 탐색
    
    ##부품찾기
    #총 부품수 1 <= N <= 1,000,000
    #찾는 부품수 1 <= M <= 100,000
    #있으면 yes, 없으면 no
    
    #방법_01 (이진탐색)
    def binary_search(array, target, start, end):
        while start <= end:
            mid = (start + end) //2
            if array[mid] == target:
                return mid
            elif array[mid] > target:
                end = mid-1
            else:
                start = mid + 1
            return None
    # 가게의 부품수 n  
    n = int(input())
    # 가게의 전체 부품수를 공백으로 구분하여 입력
    array = list(map(int, input().split()))
    array.sort() # 이진 탐색을 위해 정렬
    
    # 손님이 확인하는 부품수 m
    m = int(input())
    # 손님이 요청한 부품 목록
    x = list(map(int, input().split()))
    
    #하나씩 확인
    for i in x:
        result = binary_search(array, i, 0, n-1)
        if result != None:
            print('yes', end=' ')
        else:
            print('no', end=' ')
    
    #방법02 계수정렬
    n = int(input())
    array = [0] * 1000001
    
    #가게의 부품번호
    for i in input().split():
        array[int(i)] = 1
    
    #손님 요청    
    m = int(input())
    x = list(map(int, input().split()))
    
    for i in x:
        if array[i] == 1:
            print('yes', end=' ')
        else:
            print('no', end=' ')
    
    #방법03 집합 자료형(set())이용
    n = int(input())
    array = set(map(int, input().split()))
    #손님 요청    
    m = int(input())
    x = list(map(int, input().split()))
    
    #하나씩 확인
    for i in x:
        if i in array:
            print('yes', end=' ')
        else:
            print('no', end=' ')

def study_04():
    #떡볶이 떡 만들기
    #이진탐색 문제, 파라메드릭 서치(결정 문제는 예 혹은 아니오로 대답하는 문제)
    #높이를 0~최대값 의 중간값으로 해야 시간 절약 
    #떡볶이양에 따라 자를 위치를 경정해야하기 때문에 반복문이 효율적
    n, m = list(map(int, input().split(' '))) #n:떡갯수, m:원하는 길이
    array = list(map(int, input().split()))
    
    start = 0
    end = max(array) #최대값
    
    result = 0
    while(start <= end):
        total = 0
        mid = (start + end) // 2
        for x in array:#자르기
            if x > mid: #잘랐을때 떡의 양
                total += x - mid
        if total < m: #부적한경우
            end = mid - 1
        else: #충분한 경우
            result = mid
            start = mid +1    
    print(result)        
            
####### 실행용
study_04()
