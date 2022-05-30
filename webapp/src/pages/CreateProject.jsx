import { useEffect, useState } from "react"

const url = 'http://localhost:8080/projects'

const postBody = {
    code:"",
    projectName:"",
    description:"",
    businessSector:"",
    typology:"",
    customer:"",
    startDate:"",
    endDate:"",
    numberOfSprints:"",
    budget:"",
    projectStatus:"",
    sprintDuration:""
}


export default function CreateProject(){

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
        setResponse(res._embedded['Projects'])
        setHeader(Object.keys(res._embedded['Projects'][0]))
    })
    .catch(err => console.log(err))
}, [])

const getData = response.map((row, index) => {
        return (
            <tr key={index} style={{ cursor: "pointer",textTransform:"capitalize" }}>
                <td>{row.code}</td>
                <td>{row.projectName}</td>
                <td>{row.description}</td>
                <td>{row.customer}</td>
                <td>{row.businessSector}</td>
                <td>{row.typology}</td>
                <td>{row.numberOfSprints}</td>
                <td>{row.budget}</td>
                <td>{row.projectStatus}</td>
                <td>{row.startDate}</td>
                <td>{row.endDate}</td>
                <td>{row.sprintDuration}</td>
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
        <h1>Projects</h1>
        <table>
            <thead>
                <tr style={{textTransform: "uppercase"}} key="headers">
                    {getHeaders}
                </tr>
            </thead>
            <tbody>{getData}</tbody>
        </table>

        <form onSubmit={() => makePostRequest()}>
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[1]}</label>
            <input type="text" name={Object.keys(body)[1]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[2]}</label>
            <input type="text" name={Object.keys(body)[2]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[3]}</label>
            <input type="text" name={Object.keys(body)[3]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[4]}</label>
            <input type="text" name={Object.keys(body)[4]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[5]}</label>
            <input type="text" name={Object.keys(body)[5]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[6]}</label>
            <input type="date" name={Object.keys(body)[6]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[7]}</label>
            <input type="date" name={Object.keys(body)[7]} onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[8]}</label>
            <input type="number" name={Object.keys(body)[8]} defaultValue="0" onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[9]}</label>
            <input type="number" name={Object.keys(body)[9]} defaultValue="0" onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br />
            <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}>{Object.keys(body)[11]}</label>
            <input type="number" name={Object.keys(body)[11]} defaultValue="0" onChange={(e) => 
                setBody({...body,[e.target.name]:e.target.value})}></input><br /><br />
            <button type="submit">Create Project</button>
        </form>
    </>
)

}