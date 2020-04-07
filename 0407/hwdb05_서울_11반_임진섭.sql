use scott;

select * from emp;
select * from dept;

-- 1. 부서위치가 CHICAGO인 모든 사원에 대해 이름,업무,급여 출력하는 SQL을 작성하세요.
select a.ename, a.job, a.sal
from emp a left outer join dept b on a.deptno = b.deptno
where b.loc = 'CHICAGO';

-- 2. 부하직원이 없는 사원의 사원번호,이름,업무,부서번호 출력하는 SQL을 작성하세요.
select empno, ename, job, deptno
from emp
where empno not in (select mgr from emp where mgr is not null);

-- 3. BLAKE와 같은 상사를 가진 사원의 이름,업무,상사번호 출력하는 SQL을 작성하세요.
select ename, job, mgr
from emp
where 1=1
and mgr = (select mgr from emp where ename = 'BLAKE')
and ename != 'BLAKE';

-- 4. 입사일이 가장 오래된 사람 5명을 검색하세요.
select ename, hiredate
from emp
order by hiredate
limit 5;

-- 5. JONES 의 부하 직원의 이름, 업무, 부서명을 검색하세요.
select a.ename, a.job, c.dname
from (emp a inner join emp b) right outer join dept c on a.mgr = b.empno 
and a.deptno = c.deptno
where b.ename = 'JONES';