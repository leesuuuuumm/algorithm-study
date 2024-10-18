select p.id
from ecoli_data p
join ecoli_data c on p.parent_id = c.id 
join ecoli_data cc on c.parent_id = cc.id
where cc.parent_id is null
order by p.id
