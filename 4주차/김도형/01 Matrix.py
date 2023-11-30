class Solution:
    def updateMatrix(self, mat: List[List[int]]) -> List[List[int]]:
        m, n = len(mat), len(mat[0])
        dir = [[0, 1], [0, -1], [1, 0], [-1, 0]]
        q = deque([])
        visited = set()

        for i in range(m):
            for j in range(n):
                if mat[i][j] == 0:
                    q.append([i, j])
                    visited.add((i, j))
                else:
                    mat[i][j] = -1

        while q:
            r, c = q.popleft()
            for dr, dc in dir:
                nx, ny = r + dr, c + dc
                if 0 <= nx < m and 0 <= ny < n and (nx, ny) not in visited:
                    mat[nx][ny] = mat[r][c] + 1
                    q.append([nx, ny])
                    visited.add((nx, ny))
        return mat
