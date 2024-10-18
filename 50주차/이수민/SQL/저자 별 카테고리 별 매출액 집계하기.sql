SELECT a.author_id, author_name, category, sum((sales*price))
from book_sales s
join book b on s.book_id = b.book_id 
join Author a on a.author_id = b.author_id
where year(s.sales_date) = 2022 and month(s.sales_date) = 1
group by category, author_name
order by a.author_id, category desc
