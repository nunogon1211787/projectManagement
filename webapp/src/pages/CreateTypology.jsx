import { useEffect, useState } from "react"

const url = 'http://localhost:8080/typologies'

const postBody = {
    description:"",
}


export default function CreateTypology(){

const [body, setBody] = useState(postBody)
const [response, setResponse] = useState([])
const [header, setHeader] = useState([])

//POST REQUEST TO API

const postRequest = {
    method: 'POST',
    headers:{
        'content-Type': 'application/json',
    },
    body: JSON.stringify(body),
}

const makePostRequest = () => {
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
        setResponse(res._embedded['Typologies'])
        setHeader(Object.keys(res._embedded['Typologies'][0]))
    })
    .catch(err => console.log(err))
}, [])

const getData = response.map((row, index) => {
        return (
            <tr key={index} style={{ cursor: "pointer",textTransform:"capitalize" }}>
                <td>{row.description}</td>
            </tr>
        )
        })

const getHeaders = header.map((key) => {

    if(key !== "_links"){
        return (
            <th style={{textTransform: "uppercase"}} key="headers" >{key}</th>
        )
    }

    return ""
})

return (
    <>
        <h1>Typologies</h1>
        <table>
            <thead>
                <tr key="headers">
                    {getHeaders}
                </tr>
            </thead>
            <tbody>{getData}</tbody>
        </table>

        <form onSubmit={() => makePostRequest()}>
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[0]}</label>
            <input type="text" name={Object.keys(body)[0]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br /><br />
            <button type="submit">Create Typology</button>
        </form>
    </>
)

}