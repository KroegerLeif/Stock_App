import Button from '@mui/material/Button';
import axios from "axios";

type DeleteButtonsProps = {
    productId: string;
    onDeleted: () => void;
}

export default function DeleteButton({productId, onDeleted}: DeleteButtonsProps) {


    async function handleDelete() {
        try {
            await axios.delete(`/api/products/${productId}`);
            onDeleted();
        } catch (error) {
            console.error("Fehler beim Löschen:", error);
        }
    }

    return (
        <>
            <Button variant="contained" color="error" onClick={handleDelete}>Löschen</Button>
        </>
    )
}