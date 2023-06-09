import { Box, IconButton, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Divider } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import { useState } from "react";
import EditIcon from '@mui/icons-material/Edit';

import ArticleIcon from '@mui/icons-material/Article';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';

import { Link } from "react-router-dom";

function Sivupalkki() {

    const [open, setOpen] = useState(false);

    const handleOpen = () => {
        setOpen(true);
    }

    const handleClose = () => {
        setOpen(false);
    }
    return (

        <Box>
            <IconButton onClick={handleOpen}><MenuIcon /></IconButton>
            <Drawer
                anchor="left" open={open} onClick={handleClose}>
                <List>
                    <ListItem component={Link} to="../tapahtumat">
                        <ListItemButton>
                            <ListItemIcon><EditIcon /></ListItemIcon>
                            <ListItemText primary='Muokkaa tapahtumia' />
                        </ListItemButton>
                    </ListItem>
                    <Divider />
                    <ListItem component={Link} to="../raportit">
                        <ListItemButton>
                            <ListItemIcon><ArticleIcon /></ListItemIcon>
                            <ListItemText primary="Selaa raportteja" />
                        </ListItemButton>
                    </ListItem>
                    <Divider />
                    <ListItem component={Link} to="../liput">
                        <ListItemButton>
                            <ListItemIcon><ShoppingBasketIcon /></ListItemIcon>
                            <ListItemText primary='Myy lippuja' />
                        </ListItemButton>
                    </ListItem>
                </List>
            </Drawer>
        </Box>
    );
}

export default Sivupalkki;