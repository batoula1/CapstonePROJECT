import React, { useEffect, useState } from 'react'
// import { getAllBlogs } from '../services/blogServices'
// import { BASE_URL } from '../utils/constants';
// import { Link } from 'react-router-dom';
// // import { Pagination, PaginationItem, PaginationLink } from 'reactstrap';
// import InfiniteScroll from 'react-infinite-scroll-component'

function NewFeedInfinite() {

    // const [blogs, setBlogs] = useState(null);
    // const [data, setData] = useState(null);
    // const [page, setPage] = useState(0);
    // const [error, setError] = useState(null);

    // useEffect(() => {

    //     console.log("Page number : " + page)


    //     // setLoading(true);
    //     getAllBlogs(page, 0)
    //         .then((data) => {
    //             setData(data)
    //             if (blogs == null) {
    //                 setBlogs(data.content)
    //             } else {
    //                 setBlogs((prevBlogs) => [...prevBlogs, ...data.content]);
    //             }
    //             setError(null);
    //         })
    //         .catch((err) => {
    //             setError("Something went wrong in fetching Data!")
    //             console.log("Error in fetching data -- " + err)
    //         })
    // }, [page])



    // if (error !== null) {
    //     return <div className='display-4 text-danger'>{error}</div>;
    // }


    // if (blogs == null) {
    //     return <div className=' mt-5 border-0 loading'></div>;
    // }



    // const fetchData = () => {
    //     console.log("Req for next data")
    //     setPage((prevPage) => prevPage + 1)
    // }



    //if blogs are found
    return (
        <>


        </>
    )
}


export default NewFeedInfinite