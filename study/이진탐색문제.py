##### 이진탐색문제
#이진탐색 : 탐색범위를 반으로 줄여나가면서 탐색, 내부의 데이터가 정렬이 되어야만 사용 가능 (array, target, start, end)
#bisect 클래스 : 이진탐색 라이브러리 , 빠르기 때문에 이진탐색이 아니더라도 사용하면 좋음

def study_01():
    #정렬된 배열에서 특정 수의 개수 구하기
    n , f = map(int, input().split())
    
    data = list(map(int, input().split()))
    
    data_count = data.count(f)
    if data_count == 0:
        data_count = -1
    print(data_count)
    
    ## 책풀이
    #시간 제한이 있어 이진탐색으로 
    
    #데이터의 개수
    n = len(data)
    #정렬된 수열에서 값이 x인 원소의 개수를 세는 메서드
    def count_by_value(array,x):
        
        
        #x가 처음 등장한 인덱스 계산
        a = first(array,x,0,n-1)
        
        #수열에 x가 존재하지 앟는 경우
        if a == None:
            return 0 #값이 x인 원소의 개수는 0개 임으로 0반환
        
        #x가 마지막으로 등장한 인덱스 계산
        b = last(array, x,0,n-1)
        
        #개수를 반환
        return b - a+ 1
    
    
    #처음 위치를 찾는 이진 탐색 메서드   
    def first(array, target, start, end):
        if start > end:
            return None
        mid = (start + end) // 2
        #해당 값을 가지는 원소 중에서 가장 왼쪽에 있는 경우에만 인덱스 반환
        if (mid == 0 or target > array[mid-1]) and array[mid] == target:
            return mid
        #중간점의 값 보다 찾고자 하는 값이 작거나 같은 경우 왼쪽 확인
        elif array[mid] >= target:
            return first(array, target, start, mid-1)
        #중간점의 값 보다 찾고자 하는 값이 큰 경우 오른쪽 확인
        else:
            return first(array, target, mid+1, end)
    
    #마지막 위치를 찾는 이진 탐색 메서드
    def last(array, target, start, end):
        if start > end:
            return None
        mid = (start + end) // 2
        #해당 값을 가지는 원소 중에서 가장 오른쪽에 있는 경우에만 인덱스 반환
        if (mid == n-1 or target < array[mid+1]) and array[mid] == target:
            return mid
        #중간점의 값 보다 찾고자 하는 값이 작은 경우 왼쪽 확인
        elif array[mid] > target:
            return last(array, target, start, mid-1)
        #중간점의 값 보다 찾고자 하는 값이 큰거나 같은 경우 오른쪽 확인
        else:
            return last(array, target, mid+1, end)
    
    #실행지점
    x = f
    array = data
    #값이 x인 데이터의 개수 계산
    count = count_by_value(array,x)
    
    if count == 0:
        print(-1)
    else:
        print(count)
        
    ##bisect 라이브러리 사용
    from bisect import bisect_left, bisect_right
    
    #값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
    def count_by_range(array, left_value, rigth_value):
        right_index = bisect_right(array, rigth_value)
        left_index = bisect_left(array, left_value)
        return right_index - left_index
    
    #값이[x,x]범위에 있는 데이터의 개수 계산
    count02 = count_by_range(array,x,x)
    
    if count02 == 0:
        print(-1)
    else:
        print(count02)

        
def study_02():
    ###고정점 찾기
    
    data = [-15,-4,3,8,9,13,15]
    
    def get_fixed_point(array, start, end):
        mid = (start+end) // 2
        
        if start > end:
            return -1
            
        if array[mid] == mid:
            return mid
        
        if array[mid] > mid:
            return get_fixed_point(array, start, mid-1)
        else:
            return get_fixed_point(array,mid+1,end)
        
    result = get_fixed_point(data,0,len(data)-1)
    print(result)        
        
    ##책풀이   
    
    #이진 탐색 소스코드 구현 (재귀함수)
    def binary_search(array,start, end):
        if start > end:
            return None
        mid = (start+end) // 2
        
        if array[mid] == mid:
            return mid
        #중간점이 가리키는 위치의 값보다 중간점이 작은 경우 왼쪽 확인
        elif array[mid] > mid:
            return binary_search(array, start, mid-1)
        else:
            return binary_search(array, mid+1, end)
        
    index = binary_search(data,0,len(data)-1)
    
    if index == None:
        print(-1)
    else:
        print(index)
        
        
def study_03():
    ###공유기 설치
    #책과 함께
    
    n , c = 5,3
    array = [1,2,8,4,9]
    
    #이진탐색을 위해 정렬
    array.sort()
    
    start = array[1] - array[0] #집의 좌표 중에 가장 작은 값
    end = array[-1] - array[0] #집의 좌표 중에 가장 큰 값
    result = 0
    
    while(start <= end):
        mid = (start + end) //2 # mid는 가장 인접한 두 공유기 사이의 거리(gap)를 의미
        value = array[0]
        count = 1
        #현재의 mid값을 이용해 공유기를 설치
        for i in range(1,n): #앞에서부터 차근히 설치
            if array[i] >= value + mid:
                value = array[i]
                count += 1
        if count >= c: #c개 이상의 공유기를 설치할 수 있는 경우, 거리를 증가
            start = mid +1
            result = mid # 최적의 결과를 저장
        else: #c개 이상의 공유기를 설치할 수 없는 경우, 거리를 감소
            end = mid -1
    
    print(result)
    

def study_04():
    ### 가사 검색
    words = ["frodo", "front", "frost", "frozen", "frame", "kakao"]
    queries = ["fro??", "????o", "fr???", "fro???", "pro?"]    
    
    result = [0 for _ in range(len(queries))]
    
    for i in range(len(queries)):
        search = queries[i]
        count = 0
        for word in words:
            #길이가 같은지
            if len(search) == len(word):
                check = 0
                for j in range(len(search)):
                    if search[j] == word[j] or search[j] == "?":
                        check += 1
                if check == len(search):
                    count += 1        
        result[i] = count
    
    print(result)
    
    ## 책풀이
    #이진 탐색을 사용
    from bisect import bisect_left, bisect_right
    
    #값이 [left_value, right_vlaue]인 데이터의 개수를 반환하는 함수
    def count_by_range(a, left_value, right_vlaue):
        right_index = bisect_right(a, right_vlaue)
        left_index = bisect_left(a,left_value)
        return right_index - left_index
    
    #모든 단어르 길이마다 나누어서 저장하기 위한 리스트
    array = [[] for _ in range(10001)]
    #모든 단어를 길이마다 나누어서 뒤집어 저장하기 위한 리스트
    reverse_array = [[] for _ in range(10001)]
    
    def solution(words, queries):
        answer = []
        for word in words: #모든 단어를 접미사 와이들 카드 배열, 접두사 와일드카드 배열에 각각 삽입
            array[len(word)].append(word) #단어를 삽입
            reverse_array[len(word)].append(word[::-1]) # 단어를 뒤집어서 삽입
        
        for i in range(10001): #이진 탐색을 위행하기 위해 각 단어 리스트 정렬 수행
            array[i].sort()
            reverse_array[i].sort()
        
        for q in queries: #쿼리를 하니씩 확인하며 처리
            if q[0] != '?': #접미사에 와일드카드가 붙은 경우
                res = count_by_range(array[len(q)], q.replace('?','a'), q.replace('?', 'z'))
            #접두사에 와일드카드가 붙은 경우
            else:
                res = count_by_range(reverse_array[len(q)], q[::-1].replace('?','a'), q[::-1].replace('?','z'))
            #검색된 단더의 개수를 저장
            answer.append(res)
        return answer
    
    print(solution(words, queries))
        
study_04()