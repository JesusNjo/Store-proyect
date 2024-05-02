export interface Client{
    id:string,
    name:string,
    username:string,
    lastName:string,
    email:string,
    gender?: Gender,
    address:string,
    password:string
}

interface Gender{
    MALE:"male",
    FEMALE:"female",
    OTHER:"other"
}

export interface LoginRq{
    username:string,
    password:string
}