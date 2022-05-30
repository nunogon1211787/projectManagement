import { useState } from "react"

const initialData = {}

export default function Form(props){

    const inputLabel = Object.keys(props.label)

    const inputRules = props.rules

    const [data, setData] = useState(initialData)

    const inputs = inputLabel.map ((txt, idx)=> {
            return(
                <>
                    <label style={{display:"inline-block", textTransform: "capitalize", padding:"10px"}}> {txt} </label>
                    <input key={idx} type={inputRules[idx]} name={txt} onChange={(e) => 
                setData({...data,[e.target.name]:e.target.value})} />
                    <br />
                </>
            )
        })

    return( 

        <form onSubmit={() => props.request(data)}>
            {inputs}
            <button type="submit">Submit</button>
        </form>
    )
}