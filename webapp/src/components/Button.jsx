export default function Button(props) {
    if (props.function === undefined) {
        return (
            <button
                className="btn bg-primary"
                type={props.type === undefined ? "button" : props.type}
                style={{ margin: "1%" }}>
                {props.name}
            </button>
        );
    } else {
        return (
            <button
                className="btn bg-primary"
                onClick={
                    props.function === undefined
                        ? ""
                        : () => {
                            props.function();
                        }
                }
                type={props.type === undefined ? "button" : props.type}
                style={{ margin: "1%" }}>
                {props.name}
            </button>
        );
    }
}