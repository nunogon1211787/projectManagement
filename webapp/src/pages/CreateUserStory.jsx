import { useEffect, useState } from "react"
import Form from "../components/Form"
import Table from "../components/Table"

const url = 'http://localhost:8080/userstories'

const postBody = {
    projectID:"",
    title:"",
    priority:"",
    description:"",
    timeEstimate:""
}

const inputTypes = ["text", "text", "number", "text", "number"]


export default function CreateUserStory(){

// const [body, setBody] = useState(postBody)
const [response, setResponse] = useState([])
const [header, setHeader] = useState([])

//POST REQUEST TO API

// const postRequest = {
//     method: 'POST',
//     headers:{
//         'content-Type': 'application/json',
//     },
//     body: JSON.stringify(body),
// }

const makePostRequest = (data) => {

    const postRequest = {
        method: 'POST',
        headers:{
            'content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    }

    fetch(url, postRequest)
    .then(res => res.json())
    .then()
    .catch(err => console.log(err))
}


//GET REQUEST TO API
useEffect(() => {
    fetch(url)
    .then(res => res.json())
    .then((res) => {
        setResponse(res._embedded['User Stories'])
        setHeader(Object.keys(res._embedded['User Stories'][0]))
    })
    .catch(err => console.log(err))
}, [])

const getData = response.map((row, index) => {
        return (
            <tr key={index} style={{ cursor: "pointer",textTransform:"capitalize" }}>
                <td>{row.id}</td>
                <td>{row.priority}</td>
                <td>{row.description}</td>
                <td>{row.timeEstimate}</td>
            </tr>
        )
        })

const getHeaders = header.map((key) => {

    if(key !== "_links"){
        return (
            <th style={{textTransform: "uppercase"}}>{key}</th>
        )
    }

    return ""
})

return (
    <>
        <h1>User Story</h1>
        <table>
            <thead>
                <tr style={{textTransform: "uppercase"}} key="headers">
                    {getHeaders}
                </tr>
            </thead>
            <tbody>{getData}</tbody>
        </table>
        <Table />

        {/* <form onSubmit={() => makePostRequest()}>
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[0]}</label>
            <input type="text" name={Object.keys(body)[0]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[1]}</label>
            <input type="text" name={Object.keys(body)[1]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[2]}</label>
            <input type="number" name={Object.keys(body)[2]} min="1" max="5" defaultValue="0" onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[3]}</label>
            <input type="text" name={Object.keys(body)[3]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[4]}</label>
            <input type="number" name={Object.keys(body)[4]} defaultValue="0" onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br /><br />
            <button type="submit">Create User Story</button>
        </form> */}
        <Form label={postBody} rules={inputTypes} request={makePostRequest} />
    </>
)

}