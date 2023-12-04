# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        tortoise = hare = head  # 토끼와 거북이는 같은 출발선 상에서 시작한다.
        while tortoise != None and hare != None:  # 토끼와 거북이가 null을 만나지 않는다면
            if hare.next == None:  # 토끼가 앞서기때문에 토끼가 null을 만난다면
                return None  # None
            hare = hare.next.next
            tortoise = tortoise.next
            if hare == tortoise:  # 토끼와 거북이가 만난다면 (사이클이 존재한다면)
                hare = head  # 토끼는 다시 처음으로 이동
                while hare != tortoise:  # 토끼와 거북이가 만날때까지
                    hare = hare.next  # 1칸 이동
                    tortoise = tortoise.next  # 1칸 이동
                return hare
        return None
