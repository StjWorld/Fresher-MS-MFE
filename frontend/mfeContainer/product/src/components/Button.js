import className from "classnames";



function Button({
    children,
    primary,
    secondary,
    success,
    warning,
    danger,
    outline,
    rounded,
    ...rest
}) {

    const classes = className(' flex item-center px-3 py-1.5. border',{
        'border-blue-500 bg-blue-500 hover:bg-blue-800 text-white mb-3':primary,
        'border-gray-900 bg-gray-800 hover:bg-gray-600 text-white mt-1 float-right mr-5 h-12 w-15  items-center':secondary,
        'border-green-500 bg-green-400 text-white':success,
        'border-yellow-400 bg-yellow-300 text-white':warning,
        'border-red-500 bg-red-400 text-white':danger,
        'rounded-full': rounded,
        'bg-white': outline,
        'text-blue-500': outline && primary,
        'text-gray-900': outline && secondary,
        'text-green-500': outline && success,
        'text-yellow-400': outline && warning,
        'text-red-500': outline && danger,


    });

    return <button {...rest} className={classes}>{children}</button>;
}

Button.propTypes = {
    checkVariationValue: ({ primary, secondary, success, warning, danger }) => {
        const count = Number(!!primary)
            + Number(!!secondary)
            + Number(!!success)
            + Number(!!warning)
            + Number(!!danger)

        if (count > 1) {
            return new Error('only one of props can be true')
        }

    },
};

export default Button;